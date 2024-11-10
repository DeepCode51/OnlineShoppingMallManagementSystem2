package cn.bdqn.dao.product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.dao.BaseDao;
import cn.bdqn.entity.Product;
import cn.bdqn.params.ProductParam;
import cn.bdqn.util.EmptyUtils;

public class ProductDaoImpl extends BaseDao implements ProductDao {
	
	public ProductDaoImpl(Connection connection) {
		super(connection);
		
	}

	 public Product tableToClass(ResultSet rs) throws Exception {
	        Product product = new Product();
	        //id
	        product.setId(rs.getInt("id"));
	        //名称
	        product.setName(rs.getString("name"));
	        //描述
	        product.setDescription(rs.getString("description"));
	        //价格
	        product.setPrice(rs.getFloat("price"));
	        //库存
	        product.setStock(rs.getInt("stock"));
	        //一级二级三级分类
	        product.setCategoryLevel1Id(rs.getInt("categoryLevel1Id"));
	        product.setCategoryLevel2Id(rs.getInt("categoryLevel2Id"));
	        product.setCategoryLevel3Id(rs.getInt("categoryLevel3Id"));
	        //图片
	        product.setFileName(rs.getString("fileName"));
	        return product;
	    }

	 
	//根据条件查询商品列表
	@Override
	public List<Product> queryProductList(ProductParam params) throws SQLException {
		//商品分类的集合
		List<Product> list=new ArrayList<Product>();
		StringBuffer sql=new StringBuffer("select * from easybuy_product where 1=1");
		//？的集合
		List<Object> paramsList=new ArrayList<Object>();
		ResultSet rs=null;
		//通过关键字查询
		try {
			if(EmptyUtils.isNotEmpty(params.getKeyword())){
				sql.append(" and name like ?");
				paramsList.add("%"+params.getKeyword()+"%");
			}
			//通过所属的分类查询
			if(EmptyUtils.isNotEmpty(params.getCategoryId())){
				sql.append(" and (categoryLevel1Id=? or categoryLevel2Id=? or categoryLevel3Id=?)");
				paramsList.add(params.getCategoryId());
				paramsList.add(params.getCategoryId());
				paramsList.add(params.getCategoryId());
			}
			//是否进行排序
			if(EmptyUtils.isNotEmpty(params.getSort())){
				sql.append(" order by "+params.getSort());
			}
			//分页显示
			if(!params.isPage()){
				sql.append(" limit "+params.getStartIndex()+","+params.getPageSize());
			}
			//查询
			rs=executeQuery(sql.toString(), paramsList.toArray());
			while(rs.next()){
				Product product=tableToClass(rs);
				list.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			this.closeResoure();
		}
		return list;
	}
	//根据条件查询商品数量
	@Override
	public int queryProductCont(ProductParam params) throws SQLException{
		Integer num=0;
		StringBuffer sql=new StringBuffer("select count(*) `count` from easybuy_product where 1=1");
		List<Object> paramsList=new ArrayList<Object>();
		ResultSet rs=null;
		//通过关键字查询
		try {
			if(EmptyUtils.isNotEmpty(params.getKeyword())){
				sql.append(" and name like ?");
				paramsList.add("%"+params.getKeyword()+"%");
			}
			//通过所属的分类查询
			if(EmptyUtils.isNotEmpty(params.getCategoryId())){
				sql.append(" and (categoryLevel1Id=? or categoryLevel2Id=? or categoryLevel3Id=?)");
				paramsList.add(params.getCategoryId());
				paramsList.add(params.getCategoryId());
				paramsList.add(params.getCategoryId());
			}
			//查询
			rs=executeQuery(sql.toString(), paramsList.toArray());
			while(rs.next()){
				num=rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally{
			this.closeResoure();
		}
		return num;
		
	}
	//根据ID查询商品
	@Override
	public Product getProductById(Integer id) throws Exception {
		String sql = " select id,name,description,price,stock,categoryLevel1Id,categoryLevel2Id,categoryLevel3Id,fileName,isDelete from easybuy_product where id = ? ";
		ResultSet resultSet = null;
		Product product = null;
		try {
			Object params[] = new Object[] { id };
			resultSet = this.executeQuery(sql, params);
			while (resultSet.next()) {
				product = tableToClass(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResoure();
		}
		return product;
	}
  //库存的更新
	@Override
	public void updateStock(Integer id, Integer quantity) {
        try {
        	Object[] params = new Object[] {quantity,id};
        	String sql = " update easybuy_product set stock=? where id=? ";
    		this.executeUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
			this.closeResoure();
		}
    }

	@Override
	public Integer queryProductCount(ProductParam params) throws Exception {
		List<Object> paramsList=new ArrayList<Object>();   
		Integer count=0;
		StringBuffer sql=new StringBuffer("  select count(*) count from easybuy_product where 1=1 ");
		if(EmptyUtils.isNotEmpty(params.getName())){
			sql.append(" and name = ? ");
			paramsList.add(params.getName());
		}
		if(EmptyUtils.isNotEmpty(params.getKeyword())){
			sql.append(" and name like ? ");
			paramsList.add("%"+params.getKeyword()+"%");
		}
		ResultSet resultSet = this.executeQuery(sql.toString(),paramsList.toArray());
		try {
			while (resultSet.next()) {
				count=resultSet.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResoure();
		}
		return count;
	}

	@Override
	public void save(Product product) throws Exception {
		Integer id=0;
		String sql=" insert into easybuy_product(name,description,price,stock,categoryLevel1Id,categoryLevel2Id,categoryLevel3Id,fileName,isDelete) values(?,?,?,?,?,?,?,?,?) ";
        try {
            Object param[]=new Object[]{product.getName(),product.getDescription(),product.getPrice(),product.getStock(),product.getCategoryLevel1Id(),product.getCategoryLevel2Id(),product.getCategoryLevel3Id(),product.getFileName(),0};
            id=this.executeInsert(sql,param);
            product.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
			this.closeResoure();
		}
		
	}

	@Override
	public void update(Product product) throws Exception {
		try {
        	Object[] params = new Object[] {product.getName(),product.getFileName(),product.getCategoryLevel1Id(),product.getCategoryLevel2Id(),product.getCategoryLevel3Id(),product.getId()};
        	String sql = " update easybuy_product set name=?,fileName=?,categoryLevel1Id=?,categoryLevel3Id=?,categoryLevel3Id=? where id=? ";
    		this.executeUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
			this.closeResoure();
		}
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		String sql = " delete from easybuy_product where id = ? ";
		Object params[] = new Object[] { id };
		try{
			this.executeUpdate(sql.toString(), params);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResoure();
		}
	}
	
}

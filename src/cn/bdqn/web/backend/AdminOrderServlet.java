package cn.bdqn.web.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bdqn.entity.Order;
import cn.bdqn.entity.OrderDetail;
import cn.bdqn.params.OrderDetailParam;
import cn.bdqn.params.OrderParams;
import cn.bdqn.service.order.OrderService;
import cn.bdqn.service.order.OrderServiceImpl;
import cn.bdqn.util.EmptyUtils;
import cn.bdqn.util.Pager;
import cn.bdqn.web.pre.AbstractServlet;
@WebServlet(urlPatterns={"/admin/order"},name="AdminOrderServlet")
public class AdminOrderServlet extends AbstractServlet {
    private OrderService orderService;
    //查询 当前 用户的订单
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 //获取用户id
        String userId=request.getParameter("userId");
        //获取当前页数
        String currentPageStr = request.getParameter("currentPage");
        //获取页大小
        String pageSize = request.getParameter("pageSize");
        int rowPerPage  = EmptyUtils.isEmpty(pageSize)?10:Integer.parseInt(pageSize);
        int currentPage = EmptyUtils.isEmpty(currentPageStr)?1:Integer.parseInt(currentPageStr);
        OrderParams params =new OrderParams();
        if(EmptyUtils.isNotEmpty(userId)){
        	params.setUserId(Integer.parseInt(userId));
        }
        //总条数
        int total=orderService.getOrderRowCount(params);
       // admin/order
        Pager pager=new Pager(total,rowPerPage,currentPage);
        pager.setUrl("/admin/order?action=index&userId="+userId);
        List<Order> orderList=orderService.queryOrderList(Integer.parseInt(userId),pager);
        request.setAttribute("orderList", orderList);
        request.setAttribute("pager", pager);
        request.setAttribute("menu", 1);
        return "/backend/order/orderList";
	}

	public void init() throws ServletException {
		orderService=new OrderServiceImpl();
	}
	   /**
     * 查询订单明细
     * @param request
     * @param response
     * @return
     */
    public String queryOrderDeatil(HttpServletRequest request,HttpServletResponse response)throws Exception{
        String orderId=request.getParameter("orderId");
        OrderDetailParam params =new OrderDetailParam();
        params.setOrderId(Integer.parseInt(orderId));
        List<OrderDetail> orderDetailList=orderService.queryOrderDetailList(params);
        request.setAttribute("orderDetailList", orderDetailList);
        request.setAttribute("menu", 1);
        return "/backend/order/orderDetailList";
    }
    //查询全部的订单  只有管理员才有
    public String queryAllOrder(HttpServletRequest request,HttpServletResponse response)throws Exception{
       //获取当前页数
        String currentPageStr = request.getParameter("currentPage");
        //获取页大小
        String pageSize = request.getParameter("pageSize");
        int rowPerPage  = EmptyUtils.isEmpty(pageSize)?10:Integer.parseInt(pageSize);
        int currentPage = EmptyUtils.isEmpty(currentPageStr)?1:Integer.parseInt(currentPageStr);
        OrderParams params =new OrderParams();
        int total=orderService.getOrderRowCount(params);
        Pager pager=new Pager(total,rowPerPage,currentPage);
        pager.setUrl("/admin/order?action=queryAllOrder");
        List<Order> orderList=orderService.queryOrderList(pager);
        request.setAttribute("orderList", orderList);
        request.setAttribute("pager", pager);
        request.setAttribute("menu", 9);
        return "/backend/order/orderList";
    }

	@Override
	public Class getServletClass() {
		
		return AdminOrderServlet.class;
	}

}

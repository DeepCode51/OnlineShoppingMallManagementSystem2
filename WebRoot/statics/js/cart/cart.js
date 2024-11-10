//添加购物车
function addCartByParam(entityId,quantity){
	$.ajax({
		url:contextPath+"/CartServlet",
		method:"post",
		data:{
			action:"add",
			entityId:entityId,
			quantity:quantity
			
		},
		success:function(jsonStr){
			var result=eval("("+jsonStr+")");
			if(result.status==1){
				showMessage("操作成功");
				refreshCart();
			}else{
				showMessage("添加不成功");
			}
		}
	});
}

function refreshCart(){
	$.ajax({
		url:contextPath+"/CartServlet",
		method:"post",
		data:{
			action:"refreshCart"	
		},
		success:function(jsonStr){
			$("#searchBar").html(jsonStr);
		}
	});
}

function settlement1(){
	$.ajax({
		url:contextPath+"/CartServlet",
		method:"post",
		data:{
			action:"settlement1"	
		},
		success:function(jsonStr){
			$("#settlement").html(jsonStr);
		}
	});
}

function settlement2(){
	$.ajax({
		url:contextPath+"/CartServlet",
		method:"post",
		data:{
			action:"settlement2"	
		},
		success:function(jsonStr){
			$("#settlement").html(jsonStr);
		}
	});
}
//生成订单
function settlement3(){
    //判断地址
    var addressId=$("input[name='selectAddress']:checked").val();
    var newAddress=$("input[name='address']").val();
    var newRemark=$("input[name='remark']").val();
    if(addressId=="" || addressId==null){
        showMessage("请选择收货地址");
        return;
    }else if(addressId=="-1"){
        if(newAddress=="" || newAddress==null){
            showMessage("请填写新的收货地址");
            return;
        }else if(newAddress.length<=2 || newAddress.length>=50){
            showMessage("收货地址为2-50个字符");
            return;
        }
    }
    $.ajax({
        url: contextPath + "/CartServlet",
        method: "post",
        data: {
            action: "settlement3",
            addressId:addressId,
            newAddress:newAddress,
            newRemark:newRemark
        },
        success: function (jsonStr) {
        	if(jsonStr.substr(0,1)=="{"){
        		var result = eval("(" + jsonStr + ")");
        		showMessage(result.message);
        	}else{
        		$("#settlement").html(jsonStr);
        	}
        }
    });
}
//减商品
function subQuantity(obj,entityId){
	var quantity=Number(getPCount(obj))-1;
	if(quantity==0){
		quantity=1;
	}	
	modifyCart(entityId,quantity,obj);
}
//加商品
function addQuantity(obj,entityId,stock){
	var quantity=Number(getPCount(obj))+1;
	if(quantity>stock){
		showMessage("库存不足");
		return;
	}
	modifyCart(entityId,quantity,obj);
}
//修改购物车商品的数量
function modifyCart(entityId,quantity,obj){
	$.ajax({
		url:contextPath+"/CartServlet",
		method:"post",
		data:{
			action:"modifyCart"	,
			entityId:entityId,
			quantity:quantity
		},
		dataType:"json",
		success:function(jsonStr){
			if(jsonStr.status==1){
				obj.parent().find(".car_ipt").val(quantity);
				settlement1();
			}else{
				showMessage(jsonStr.message);
			}
			
		}
	});
}
//删除
function removeCart(entityId){
	$.ajax({
		url:contextPath+"/CartServlet",
		method:"post",
		data:{
			action:"modifyCart"	,
			entityId:entityId,
			quantity:0
		},
		dataType:"json",
		success:function(jsonStr){
			if(jsonStr.status==1){
				settlement1();
			}else{
				showMessage(jsonStr.message);
			}
			
		}
	});
}
//添加收藏
/*function addFavorite(entityId){
	$.ajax({
		url:contextPath+"/FavoriteServlet",
		method:"post",
		data:{
			action:"addFavorite",
			id:entityId
		},
		dataType:"json",
		success:function(jsonStr){
			
		}
	});
}
//添加收藏成功后展示
function favoriteList(){
	$.ajax({
		url:contextPath+"/FavoriteServlet",
		method:"post",
		data:{
			action:"favoriteList",
			id:entityId
		},
		dataType:"json",
		success:function(jsonStr){
			
		}
	});
}*/
//
function addCart(){
    var entityId=$("input[name='entityId']").val();
    var quantity=$("input[name='quantity']").val();
    //添加到购物车
    addCartByParam(entityId,quantity);
}
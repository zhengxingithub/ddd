<%@page contentType="text/html; utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Purple Admin</title>
  <!-- plugins:css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/iconfonts/mdi/css/materialdesignicons.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/css/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- plugin css for this page -->
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" />
  <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
  <script>
    $(function(){
       $("#exampleInputUsername1").blur(function(){
           $.ajax({
               url:"/user/check",
               type:'post',
               data:{"username":$("#exampleInputUsername1").val()},
               dataType:'json',
               success:function(data){
                   if(!data.status){
                       $("#exampleInputUsername1").val(data.msg);
                   }
               }
           })
       })
        $("#sub").click(function(){
            if($("#agree").is(':checked')){$("form").submit();
            }else{
              $("#agreeinfo").text("请同意！！！")
            }
        })

    })
  </script>
</head>

<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
      <div class="content-wrapper d-flex align-items-center auth">
        <div class="row w-100">
          <div class="col-lg-4 mx-auto">
            <div class="auth-form-light text-left p-5">
              <div class="brand-logo">
                <img src="${pageContext.request.contextPath}/images/logo.svg">
              </div>
              <h4>第一次来?</h4>
              <h6 class="font-weight-light">快来玩呀</h6>
              <form class="pt-3" action="${pageContext.request.contextPath}/user/reg" method="post">
                <div class="form-group">
                  <input type="text" class="form-control form-control-lg" name="username" id="exampleInputUsername1" placeholder="请输入账号">
                </div>
                <div class="form-group">
                  <input type="email" class="form-control form-control-lg" name="email" id="exampleInputEmail1" placeholder="请输入邮箱">
                </div>
                <div class="form-group">
                  <input type="password" class="form-control form-control-lg" name="password" id="exampleInputPassword1" placeholder="请输入密码">
                </div>
                <div class="mb-4">
                  <div class="form-check">
                    <label class="form-check-label text-muted">
                      <input id="agree" type="checkbox" class="form-check-input">
                      我同意以下条款
                    </label>
                    <span id="agreeinfo"></span>
                  </div>
                </div>
                <div class="mt-3">
                  <a id="sub" class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn" >注册</a>
                </div>
                <div class="text-center mt-4 font-weight-light">
                  老嫖客? <a href="${pageContext.request.contextPath}/user/toLogin" class="text-primary">登录</a>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <!-- content-wrapper ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->
  <!-- plugins:js -->
  <script src="${pageContext.request.contextPath}/vendors/js/vendor.bundle.base.js"></script>
  <script src="${pageContext.request.contextPath}/vendors/js/vendor.bundle.addons.js"></script>
  <!-- endinject -->
  <!-- inject:js -->
  <script src="${pageContext.request.contextPath}/js/off-canvas.js"></script>
  <script src="${pageContext.request.contextPath}/js/misc.js"></script>
  <!-- endinject -->
</body>

</html>

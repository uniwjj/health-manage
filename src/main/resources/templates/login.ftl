<html lang="en">
<head>
  <meta charset="utf-8">
  <title>登录</title>
  <link rel="stylesheet" type="text/css" href="${request.contextPath}/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="${request.contextPath}/css/signin.css">
</head>
<body>
<div class="container">
  <form class="form-signin" method="post" action="${request.contextPath}/auth/login-process">
    <h2 class="form-signin-heading">请登录</h2>
    <#if error?? >
      <div class="alert alert-danger" role="alert">用户名或密码错误</div>
    </#if>
    <p>
      <label for="username" class="sr-only">用户名</label>
      <input type="text" id="username" name="username" class="form-control" placeholder="用户名" required autofocus>
    </p>
    <p>
      <label for="password" class="sr-only">密码</label>
      <input type="password" id="password" name="password" class="form-control" placeholder="密码" required>
    </p>
    <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
  </form>
</div>
</body>
</html>

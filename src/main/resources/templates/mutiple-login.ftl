<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Mammut</title>
</head>

<body>
<div class="page">
	<div class="title">请登录</div>
    <#list productList as product>
			<a class="row" href=${product.url}>
				<div class="logo">
					<img src=${product.logo} width="28" alt=${product.name} class="logo-img"/>
				</div>
				<div>${product.name}</div>
			</a>
    </#list>
</div>
</body>
<style>
	a {
		text-decoration: none;
		color: #333;
	}

	.page {
		display: flex;
		flex-direction: column;
		align-items: center;
		margin-top: 40px;
	}

	.title {
		margin-bottom: 40px;
		font-size: 22px;
	}

	.row {
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		margin-bottom: 16px;
		border: 1px solid #D9DDE3;
		border-radius: 3px;
		width: 25%;
		padding: 8px;
	}

	.row:hover {
		cursor: pointer;
		background-color: rgba(0, 115, 251, 0.10);
		color: #0075FF;
	}

	.logo {
		height: 28px;
		width: 28px;
		margin-right: 16px;
	}

	.logo-img {
		outline: none;
		display: block;
		border: none;
		font-size: 14px;
		font-family: Hiragino Sans GB;
	}

</style>

</html>

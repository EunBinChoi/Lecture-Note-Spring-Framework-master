<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${cp}/resources/css/header.css">

<!-- font awesome apis (icon) -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

<!-- google font apis (font) -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@600&display=swap"
	rel="stylesheet">
<title>Responsive Header</title>


<link rel="shortcut icon" href="${cp}/resources/icon/favicon.ico"
	type="image/x-icon">
<link rel="icon" href="${cp}/resources/icon/favicon-16x16.png"
	sizes="16x16">
<link rel="icon" href="${cp}/resources/icon/favicon-32x32.png"
	sizes="32x32">
<link rel="icon" href="${cp}/resources/icon/favicon-96x96.png"
	sizes="96x96">


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${cp}/resources/js/header.js" defer></script>


</head>
<body>
	<header class="header">
		<div class="header__logo">
			<a href="${cp}/"><i class="fas fa-camera"></i></a> <a href="${cp}/">Sallys</a>
		</div>


		<ul class="header__nav">
			<li><a href="#">${messageSource.home}</a></li>
			<li><a href="#">${messageSource.board}</a></li>
			<li><a href="#">${messageSource.album}</a></li>
			<li><a href="#">${messageSource.FAQ}</a></li>
		</ul>

		<ul class="header__icons">
			<li><i class="fab fa-twitter"></i></li>
			<li><i class="fab fa-facebook"></i></li>
		</ul>

		<div class="header__icons__toggle">
			<a href="#" class="header__toggleBtn"> <i class="fas fa-bars"></i>
			</a>
		</div>
	</header>
</body>
</html>
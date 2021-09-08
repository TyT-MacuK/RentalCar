<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">

<title>Create an account</title>
</head>

<body>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col col-lg-6 mt-3">
				<h3 class="text-center">Sing up</h3>
				<form>
					<div class="mb-2">
						<label for="inputEmail1" class="form-label">Email address</label>
						<input type="email" class="form-control" id="inputEmail1"
							aria-describedby="emailHelp">
						<div id="emailHelp" class="form-text">We'll never share your
							email with anyone else.</div>
					</div>
					<div class="mb-4">
						<label for="inputPassword1" class="form-label">Password</label> <input
							type="password" class="form-control" id="inputPassword1">
					</div>
					<div class="input-group mb-2">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputFirstAndLastName1">First and last name</span>
						</div>
						<input type="text" class="form-control"> 
						<input type="text" class="form-control">
					</div>
					<div class="form-group mt-3">
						<label for="inputDate">Date of birth</label> 
						<input type="date" class="form-control">
					</div>
					
					<div class="input-group flex-nowrap mt-4">
                        <span class="input-group-text" id="addon-wrapping">+375</span>
                        <input type="text" class="form-control" placeholder="phone number" aria-label="phone_number" aria-describedby="addon-wrapping">
                    </div>
					<div class="mb-3 form-check">
						<input type="checkbox" class="form-check-input" id="check1">
						<label class="form-check-label" for="check1">Check
							me out</label>
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
		crossorigin="anonymous"></script>
</body>
</html>
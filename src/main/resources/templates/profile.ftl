<#import "parts/common.ftl" as c>

<@c.page>
	<h5>${username}</h5>
  ${message!}
	<form method="post">
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Password:</label>
			<div class="col-sm-6">
				<input type="text" name="password" class="container-fluid" placeholder="Password"/>
			</div>
		</div>
				<div class="form-group row">
					<label class="col-sm-2 col-form-label">Email:</label>
					<div class="col-sm-6">
						<input type="email" name="email" class="container-fluid" placeholder="Email" value="${email!''}"/>
					</div>
				</div>
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		<button type="submit" class="btn btn-primary">Save</button>
	</form>
</@c.page>
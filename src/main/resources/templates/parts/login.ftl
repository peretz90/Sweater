<#macro login path isRegisterForm>
    <form action="${path}" method="post" xmlns="http://www.w3.org/1999/html">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name:</label>
            <div class="col-sm-6">
                <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                       class="container-fluid form-control ${(usernameError??)?string('is-invalid', '')}"
                       placeholder="Username"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="text" name="password" class="container-fluid form-control ${(passwordError??)?string('is-invalid', '')}" placeholder="Password"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Password:</label>
                <div class="col-sm-6">
                    <input type="text" name="password2" class="container-fluid form-control ${(password2Error??)?string('is-invalid', '')}" placeholder="Retype Password"/>
                    <#if password2Error??>
                        <div class="invalid-feedback">
                            ${password2Error}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                           class="container-fluid form-control ${(emailError??)?string('is-invalid', '')}" placeholder="Email"/>
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="col-sm-8 d-flex justify-content-sm-end flex-column">
            <#if !isRegisterForm>
                <a href="/registration" class="align-self-center mr-sm-3">Registration</a>
                <#else>
                    <div class="d-flex justify-content-sm-end">
                        <div class="g-recaptcha" data-sitekey="6LcLYrkZAAAAALz876npgukniM9Dha7zupL3RJOc"></div>
                    </div>
                    <#if captchaError??>
                        <div class="alert alert-danger" role="alert">
                            ${captchaError}
                        </div>
                    </#if>
            </#if>
            <button type="submit" class="btn btn-primary mt-sm-3"><#if isRegisterForm>Create<#else>Sign In</#if></button>
        </div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <button class="btn btn-primary" type="submit">Logged out</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</#macro>


<#import "parts/common.ftl" as c>

<@c.page>
  <div class="form-row">
    <div class="form-group col-md-6">
      <form method="get" action="/main" class="form-inline">
        <input name="filter" type="text" placeholder="Search by tag" value="${filter!}" />
        <button type="submit" class="btn btn-primary ml-2">Search</button>
      </form>
    </div>
  </div>
  <a class="btn btn-primary" data-toggle="collapse" href="#new_message" role="button" aria-expanded="false">
    Add new message
  </a>
  <div class="collapse <#if message??>show</#if>" id="new_message">
    <div class="form-group mt-3">
      <form action="/main" method="post" enctype="multipart/form-data">
        <div class="form-group">
          <input class="form-control ${(textError??)?string('is-invalid', '')}" type="text"
                 name="text" placeholder="Some text" value="<#if message??>${message.text}</#if>"/>
          <#if textError??>
          <div class="invalid-feedback">
            ${textError}
          </div>
          </#if>
        </div>
        <div class="form-group">
          <input class="form-control ${(tagError??)?string('is-invalid', '')}" type="text" name="tag" placeholder="Some tag" value="<#if message??>${message.tag}</#if>"/>
          <#if tagError??>
            <div class="invalid-feedback">
              ${tagError}
            </div>
          </#if>
        </div>
        <div class="form-group">
          <div class="custom-file">
            <input type="file" name="file" id="customFile" class="custom-file-input">
            <label class="custom-file-label" for="customFile">Choose file</label>
          </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="form-group">
          <button type="submit" class="btn btn-primary">Add message</button>
        </div>
      </form>
    </div>
  </div>
  <div class="card-columns">
    <#list messages as message>
      <div class="card my-3">
        <#if message.filename??>
          <img src="/img/${message.filename}" class="card-img-top">
        </#if>
        <div class="m-2">
          <span>${message.text}</span>
          <i>${message.tag}</i>
        </div>
        <div class="card-footer text-muted">
          ${message.authorName}
        </div>
      </div>
    <#else>
      No messages
    </#list>
  </div>
</@c.page>
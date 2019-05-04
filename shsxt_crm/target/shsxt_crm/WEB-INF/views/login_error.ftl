<#include "common.ftl" >
  <script type="text/javascript">
    alert('${errorMsg}');
    if('${uri}'.indexOf("/main")>0){
        window.location.href="${ctx}/index";
    }else{
        window.parent.location.href="${ctx}/index";
    }
  </script>

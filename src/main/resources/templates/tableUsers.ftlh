<!DOCTYPE html>
<html lang="ru">
  <head>
   
  </head>
  <body>
    <h1><b> USERS </b> </h1>
    
   <#list usersList as person>
<div>
<p><b>${person.id}</b> |  ${person.login}  | ${person.password}" </a></p>
</div>

</#list>

<form action = "/deleteUser" method = "get">
login: <input type = "text" name = "login"/> <br><br>
    <input type = "submit" value = "Удалить"/>

 </form>

    
      </body>
</html>
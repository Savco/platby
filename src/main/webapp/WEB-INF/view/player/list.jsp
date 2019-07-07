<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Welcome</title>
    <link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css"
        rel="stylesheet">
    <nav class="navbar navbar-expand-sm bg-light navbar-light">
      <ul class="navbar-nav">
        <li class="nav-item active">
          <a class="nav-link" href="/player/list/U19">U19</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/player/list/U17">U17</a>
        </li>
      </ul>
    </nav>
</head>
<body>
    <table class="table table-hover">
        <thead class="thead-light">
            <tr>
                 <th scope="col">Priezvisko</th>
                 <th scope="col">Meno</th>
                 <th scope="col">Dlh</th>
                 <th scope="col">Kategoria</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${players}" var="player">
                <tr>
                    <td><c:out value="${player.surname}"/></td>
                    <td><c:out value="${player.name}"/></td>
                    <td><fmt:formatNumber pattern="0" value = "${player.debt}"/></td>
                    <td><c:out value="${player.category}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div>
            <a class="btn btn-default" href="/add-todo">Add a Todo</a>

        </div>
        <script src="/webjars/jquery/3.0.0/jquery.min.js"></script>
        <script src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </div>
</body>
</html>

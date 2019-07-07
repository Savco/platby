<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Welcome</title>
    <link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css"
        rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        Players
                    </div>

                    <!-- /.box-header -->
                    <div class="box-body table-responsive no-padding">
                        <table class="table table-hover">
                            <thead class="thead-light">
                                <tr>
                                     <th class="col-lg-6">Priezvisko</th>
                                     <th class="column">Meno</th>
                                     <th class="column">Dlh</th>
                                     <th class="column">Kategoria</th>
                                </tr>
                             </thead>
                             <tbody>
                                <c:forEach items="${players}" var="player">
                                    <tr>
                                        <td><c:out value="${player.surname}"/></td>
                                        <td><c:out value="${player.name}"/></td>
                                        <td><c:out value="${player.debt}"/></td>
                                        <td><c:out value="${player.category}"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody></table>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
        </div>
        <div>
            <a class="btn btn-default" href="/add-todo">Add a Todo</a>

        </div>
        <script src="/webjars/jquery/3.0.0/jquery.min.js"></script>
        <script src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </div>
</body>
</html>

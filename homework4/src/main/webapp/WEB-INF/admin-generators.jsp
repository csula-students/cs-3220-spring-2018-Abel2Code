<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Admin Events</title>
  <link rel="stylesheet" type="text/css" href="../app.css">
</head>
<body>
  <h1>Incremental Game Framework</h1>
  <nav>
    <a href="./information">Game Information</a>
    |
    <a href="./generators"><strong>Generators</strong></a>
    |
    <a href="./events">
      Events
    </a>
    <a id="logout" href="./auth">Log Out</a>
  </nav>
  <main>
    <div class="container">
      <div class="fill">
        <form method='POST'>
          <p>
            Generator Name
            <br/>
            <input type="text" name='name'></input>
          </p>
          <p>
            Generator Rate
            <br/>
            <input type="text" name='rate'></input>
          </p>
          <p>
            Base Cost
            <br/>
            <input type="text" name='baseCost'></input>
          </p>
          <p>
            Unlock At
            <br/>
            <input type="text" name='unlockAt'></input>
          </p>
          <p>
            Description
            <br/>
            <textarea name='description'></textarea>
          </p>
          <button>
            {Add|Edit}
          </button>
        </form>
      </div>
      <div>
        <table>
          <tr id="tableHead">
            <th>
              Name
            </th>
            <th>
              Rate
            </th>
            <th>
              Cost
            </th>
            <th>Unlock At</th>
            <th>
              Edit|Delete
            </th>
          </tr>
          <c:forEach items="${generators}" var="generator">
            <tr>
              <td>${generator.name}</td>
              <td>${generator.rate}</td>
              <td>${generator.baseCost}</td>
              <td>${generator.unlockAt}</td>
              <td>
                <a href='../admin/generators/edit?id=${generator.id}'>Edit</a>|<a href='../admin/generators/delete?id=${generator.id}'>Delete</a>
              </td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>
  </div>
  </main>
</body>
</html>

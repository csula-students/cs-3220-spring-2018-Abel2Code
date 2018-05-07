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
    <a href="./generators">Generators</a>
    |
    <a href="./events">
      <strong>Events</strong>
    </a>
    <a id="logout" href="./auth">Log Out</a>
  </nav>
  <main>
    <div class="container">
      <div class="fill">
        <form method='POST'>
          <p>
            Event Name
            <br/>
            <input type="text" name='name'></input>
          </p>
          <p>
            Event Description
            <br/>
            <textarea name='description'></textarea>
          </p>
          <p>
            Trigger At
            <br/>
            <input type="text" name='triggerAt'></input>
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
              Description
            </th>
            <th>
              Trigger At
            </th>
            <th>
              Edit|Delete
            </th>
          </tr>
          <c:forEach items="${events}" var="event">
            <tr>
              <td>${event.name}</td>
              <td>${event.description}</td>
              <td>${event.triggerAt}</td>
              <td>
                <a href='../admin/events/edit?id=${event.id}'>Edit</a>|<a href='../admin/events/delete?id=${event.id}'>Delete</a>
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

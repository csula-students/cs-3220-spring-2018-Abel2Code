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
    <a href="./information"><strong>Game Information</strong></a>
    |
    <a href="./generators">Generators</a>
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
          <label for="name">Game Name:</label>
          <div><input type="text" name="name" value="${name}"></input></div>
          <button>Save</button>
        </form>
    </div>
  </div>
  </main>
</body>
</html>

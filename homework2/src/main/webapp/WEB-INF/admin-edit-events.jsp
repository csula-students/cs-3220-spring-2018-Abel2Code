<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Edit Event</title>
</head>
<body>
  <h1>Edit Event</h1>
  <form method='POST'>
    <label for="name">Name:</label><input type="text" id="name" name="name" value='${name}'/><br/>
    <label for="description">Description:</label><input type="text" id="description" name="description" value='${description}'/><br/>
    <label for="trigger">Trigger At:</label><input type="text" id="trigger" name="trigger" value='${triggerAt}'/><br/>
    <input type='hidden' name='id' value='${id}'/>
    <button>Change</button>
  </form>
</body>
</html>

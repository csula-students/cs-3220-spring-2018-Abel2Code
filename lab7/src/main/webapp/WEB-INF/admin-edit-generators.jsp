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
    <label for="name">Generator Name:</label><input type="text" id="name" name="name" value='${name}'/><br/>
    <label for="rate">Generator Rate:</label><input type="text" id="rate" name="rate" value='${rate}'/><br/>
    <label for="baseCost">Generator Base Cost:</label><input type="text" id="baseCost" name="baseCost" value='${baseCost}'/><br/>
    <label for="unlockAt">Unlock At:</label><input type="text" id="unlockAt" name="unlockAt" value='${unlockAt}'/><br/>
    <label for="description">Description:</label><textarea type="text" id="description" name="description">${description}</textarea><br/>
    <input type='hidden' name='id' value='${id}'/>
    <button>Change</button>
  </form>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>${gameName} Game</title>
  <link rel="stylesheet" href="game.css">
    <script>
      state = ${state};
      for(i = 0; i < state.generators.length; i++){
        state.generators[i].quantity = 0;
        state.generators[i].unlockValue = state.generators[i].unlockAt;
      }

      for(i = 0; i < state.story.length; i++){
        state.story[i].state = 'hidden';
        state.story[i].triggeredAt = state.story[i].triggerAt;
      }

      window.myDefaultState = state; // where state is passed from Controller as JSON string
    </script>
</head>
<body>
  <header>
  	<h1>${gameName}</h1>
  </header>
  <main>
    <game-story-book></game-story-book>

    <div id="resource_area">
      <div class="container">
						<game-counter class="fill"></game-counter>
            <game-button></game-button>
      </div>
    </div>

    <div id="purchase_area">
      <div class="container flex-center">
				<c:forEach var = "i" begin = "0" end = "${generatorLast}">
         	<game-generator class="generator-box" data-id="${i}"></game-generator>
      	</c:forEach>
        <%-- <game-generator class="generator-box" data-id="0"></game-generator>
				<game-generator class="generator-box" data-id="1"></game-generator>
				<game-generator class="generator-box" data-id="2"></game-generator> --%>
      </div>
    </div>
  </main>
	<script src="app.bundle.js"></script>
</body>
</html>

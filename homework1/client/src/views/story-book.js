export default function (store) {
	return class StoryBookComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			// Keeps track of which stories are visible (Only works if stories are
			// in order of increasing triggeredAt in app.js)
			this.statePos = 0;

			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (state) {
			// display story based on the state "resource" and "stories";
				let arr = [];
				for(let i = this.statePos; i < state.generators.length; i++){
					if(state.story[i].state === "visible"){
						this.statePos++;
						arr.push(state.story[i]);
					} else {
						break;
					}
				}
				this.render(arr);
		}

		connectedCallback () {
			this.initialRender();
			this.store.subscribe(this.onStateChange);
		}

		// initial DOM rendering of story itself
		initialRender () {
			this.innerHTML = `
				<div id="story_area">

				</div>
			`;
		}

		// DOM render updating of story
		render (arr) {
			arr.forEach((element)=>{
				let inner = document.querySelector('div').innerHTML += "<p>" + element.name + "</p>";
			})
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}
	};
}

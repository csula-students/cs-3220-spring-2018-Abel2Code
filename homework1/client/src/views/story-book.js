export default function (store) {
	return class StoryBookComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (state) {
			// TODO: display story based on the state "resource" and "stories";
				let arr = [];
				for(let i = 0; i < state.generators.length; i++){
					if(state.story[i].state === "visible"){
						arr.push(state.story[i]);
					}
				}
				this.render(arr);
		}

		connectedCallback () {
			this.render([]);
			this.store.subscribe(this.onStateChange);
		}

		// initial DOM rendering of story itself
		render (arr) {
			this.innerHTML = `
				<div id="story_area">

				</div>
			`;

			arr.forEach((element)=>{
				let inner = document.querySelector('div').innerHTML += "<p>" + element.name + "</p>";
			})
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}
	};
}

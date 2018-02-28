import constants from '../constants';

export default function (store) {
	return class CounterComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			this.counter = 0;

			this.store.subscribe((state, action) =>{
					this.counter = state.counter;
					this.updateHtml();
			});

			this.store.subscribe((state,action)=>{
				window.incrementalGame.state.counter = state.counter;
			})

		}

		connectedCallback () {
			// TODO: render counter inner HTML based on the store state
			this.innerHTML = `
				<h2>Lines of Code: <span id="lines_of_code">${this.counter}</span> </h2>
			`;
		}

		updateHtml(){
			// TODO: update inner HTML based on the new state
			this.querySelector('#lines_of_code').innerHTML = `${this.counter}`;
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}
	};
}

export default function (store) {
	return class CounterComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {
			this.innerHTML = `	<h2>Lines of Code: <span id="lines_of_code">${newState.counter}</span> </h2>`;
		}

		connectedCallback () {
			this.innerHTML = `
				<h2>Lines of Code: <span id="lines_of_code">0</span> </h2>
			`;
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}
	};
}

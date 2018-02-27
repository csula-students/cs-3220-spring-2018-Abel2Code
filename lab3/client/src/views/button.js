import constants from '../constants';

export default function (store) {
	return class ButtonComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			this.counter = 0;

			// this.onStateChange = this.handleStateChange.bind(this);

			this.store.subscribe((store, action)=>{
				if(action.type === constants.actions.INCREMENT_LOC){
					this.counter = store.counter;
					console.log(this.counter);
				}

			});

			// TODO: add click event to increment counter
			// hint: use "store.dispatch" method (see example component)
		}

		connectedCallback () {
			this.innerHTML = `
				<button id="generator_button" class="rounded">
					Generate
				</button>
			`;

			const action = {
				type: constants.actions.INCREMENT_LOC,
				payload: {
					quantity: 1
				}
			};

      this.querySelector('button')
          .addEventListener('click', () => {
						store.dispatch(action);
          });
    }

		disconnectedCallback () {
    }
	};
}

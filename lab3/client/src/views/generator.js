import constants from '../constants';
import Generator from '../models/generator';

export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			this.meta = store.state.generators[this.dataset.id];

			// Used to Compute New Costs
			this.generator = new Generator(this.meta);
			this.cost = this.generator.getCost();

			// TODO: subscribe to store on change event
			this.onStateChange = this.handleStateChange.bind(this);
			this.store.subscribe(this.onStateChange);
		}

		connectedCallback () {
			this.render();
    }

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
    }

		handleStateChange (state){
			for(let i = 0; i < state.generators.length; i++){
				if(state.generators[i].name === this.meta.name){
					this.updateValues(state.generators[i].quantity);
					this.render();
										break;
				}
			}
		}

		render(){
      this.innerHTML = `
					<div class=\"container\">
						<span class=\"fill generator-name\">${this.generator.name}</span>
						<span class=\"small-text\" id="generator_quantity">${this.generator.quantity}</span>
					</div>

					<p> ${this.generator.description}
					</p>

					<div id=\"generator_box_values\" class=\"container\">
						<span class=\"fill generator-name small-text\">${this.generator.rate}/60</span>
						<button class=\"rounded\" class=\"rounded\"> <span id="generator_cost">${this.cost}</span> Resource </button>
					</div>`;

			const action = {
				type: constants.actions.BUY_GENERATOR,
				payload: {
					name: this.meta.name
				}
			};

      this.querySelector('button')
          .addEventListener('click', () => {
						store.dispatch(action);
          });

		}

		updateValues(quantity){
			this.generator.quantity = quantity;
			this.cost = this.generator.getCost();
		}
	};
}

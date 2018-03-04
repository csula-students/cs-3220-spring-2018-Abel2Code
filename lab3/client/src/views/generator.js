import constants from '../constants';
import Generator from '../models/generator';

export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			this.meta = store.__state.generators[this.dataset.id];

			// TODO: render generator initial view
			this.generator = new Generator(this.meta);
			this.cost = this.generator.getCost();
			this.name = this.generator.name;
			this.store.addGenerator(this.generator);

			// TODO: subscribe to store on change event
			this.store.subscribe((state, action, name = this.name, gen = this) =>{
				if(action.type === constants.actions.BUY_GENERATOR){
					for(let i = 0; i < state.generators.length; i++){
						if(state.generators[i].name === name){
							gen.updateValues(state.generators[i].quantity);
							gen.updateHtml();
							break;
						}
					}
				}
			});
		}

		connectedCallback () {
      this.innerHTML = `
					<div class=\"container\">
						<span class=\"fill generator-name\">${this.name}</span>
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
					name: this.meta.name,
					quantity: 0
				}
			};

      this.querySelector('button')
          .addEventListener('click', () => {
						store.dispatch(action);
          });
    }

		disconnectedCallback () {

    }

		updateHtml(){
			this.querySelector('#generator_quantity').innerHTML = `${this.generator.quantity}`;
			this.querySelector('#generator_cost').innerHTML = `${this.cost}`;
		}

		updateValues(quantity){
			this.generator.quantity = quantity;
			this.cost = this.generator.getCost();
		}
	};
}

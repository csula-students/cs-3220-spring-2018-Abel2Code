import constants from '../constants';
import Generator from '../models/generator';

export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			if(this.dataset.id == 0){
				this.meta = {
					"type": "Generator",
					"name": "Intern",
					"description": "Interns generate code, but not quickly.",
					"rate": 5,
					"quantity": 0,
					"baseCost": 10,
					"unlockValue": 10
				}
			} else if(this.dataset.id == 1){
				this.meta = {
					"type": "Generator",
					"name": "Software Engineer",
					"description": "Software Engineers can write code pretty quickly",
					"rate": 10,
					"quantity": 0,
					"baseCost": 100,
					"unlockValue": 100
				}
			} else if(this.dataset.id == 2){
				this.meta = {
					"type": "Generator",
					"name": "Researcher",
					"description": "Software Engineers can write code pretty quickly",
					"rate": 20,
					"quantity": 0,
					"baseCost": 500,
					"unlockValue": 500
				}
			} else {
				this.meta = {
					"type": "Generator",
					"name": "ERROR",
					"description": "ERROR",
					"rate": 0,
					"quantity": 0,
					"baseCost": 0,
					"unlockValue": 0
				}
			}

			// TODO: render generator initial view
			this.generator = new Generator(this.meta);
			this.cost = this.generator.getCost();
			this.name = this.generator.name;
			this.store.addGenerator(this.generator);

			// TODO: subscribe to store on change event
			this.store.subscribe((state, action, name = this.name, gen = this) =>{
				if(action.type === constants.actions.BUY_GENERATOR && state.elementChanged.name === name){
					console.log("nice");
					gen.updateValues(state.elementChanged.quantity);
					gen.updateHtml();
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
			console.log("updating generator");
			this.querySelector('#generator_quantity').innerHTML = `${this.generator.quantity}`;
			this.querySelector('#generator_cost').innerHTML = `${this.cost}`;
		}

		updateValues(quantity){
			this.generator.quantity = quantity;
			console.log(this.generator.quantity + " " + quantity)
			this.cost = this.generator.getCost();
		}
	};
}

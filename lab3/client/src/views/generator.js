export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			// TODO: render generator initial view

			// TODO: subscribe to store on change event

			// TODO: add click event

		}

		connectedCallback () {
			const name = this.dataset.name;
      const message = this.dataset.message;
      this.innerHTML = '
				<div>
					<div class="container">
						<span class="fill generator-name">${name}</span>
						<span class="small-text">0</span>
					</div>

					<p> ${name}
					</p>

					<div id="generator_box_values" class="container">
						<span class="fill generator-name small-text">10/60</span>
						<button class="rounded" class="rounded"> 100 Resource </button>
					</div>
				</div>
      ';
      this.querySelector('button')
          .addEventListener('click', () => {
              console.log(this);
          });
    }

		disconnectedCallback () {
    }
	};
}

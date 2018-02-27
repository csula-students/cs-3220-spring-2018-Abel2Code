//import Generator from '../src/models/generator';
import Generator from '../src/models/generator';

export default function reducer (state, action) {
	switch (action.type) {
	case 'EXAMPLE_MUTATION':
		state.example = action.payload;
		return state;
	case 'BUY_GENERATOR':
	state.elementChanged = {};
	state.generators.forEach((element) =>{
		if(element.name === action.payload.name){
			const generator = new Generator(element);
			const cost =  generator.getCost();
			if(state.counter >= cost){
				state.counter -= cost;
				state.elementChanged = {
					"name": element.name,
					"quantity": ++element.quantity
				};
			}
		}
	});
	return state;
	case 'INCREMENT_LOC':
		state.counter += action.payload.quantity;
		return state;
	default:
		console.log(action);
		return state;
	}
}

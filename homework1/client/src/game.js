import constants from './constants';
// default interval as 1 second
const interval = 1000;

/**
 * loop is main loop of the game, which will be executed once every second (
 * based on the interval variable configuration)
 */
export function loop (store) {
	// TODO: increment counter based on the generators in the state
	// hint: read how many "generators" in store and iterate through them to
	//       count how many value to increment to "resource"
	// hint: remember to change event through `store.dispatch`
	let counter = 0;
	store.state.generators.forEach((element)=>{
		counter += (element.rate * element.quantity);
	});

	store.dispatch({
					type: constants.actions.INCREMENT,
					payload: counter
				});

	// triggers stories from story to display state if they are passed
	//       the `triggeredAt` points
	// hint: use store.dispatch to send event for changing events state
	// We could also subscribe somewhere else but every second is fine to check this.
	store.dispatch({
		type: constants.actions.CHECK_STORY
	});
	// recursively calls loop method every second
	setTimeout(loop.bind(this, store), interval);
}

// PubSub is single object for publish data to multiple subscribers
class PubSub {
    constructor () {
        this.subscribers = [];
    }

    // subscribe allows a new subscriber to listen for changes by providing
    // callback function in the parameter
    subscribe (fn) {
        this.subscribers.push(fn);
    }

    // one can publish any data to subscribers
    publish (data) {
        this.subscribers.forEach(subscriber => {
            subscriber(data);
        });
    }
}

const pubSub = new PubSub();

pubSub.subscribe(data => {
    console.log(data);
});

// Generator Button Publish (+1)
var node = document.querySelector('#generator_button').addEventListener('click', function() {
    pubSub.publish(window.incrementalGame.state.counter + 1);
});

pubSub.subscribe(newState => {
    window.incrementalGame.state.counter = newState;
});

pubSub.subscribe(linesOfCode => {
  document.querySelector('#lines_of_code').innerHTML = linesOfCode;
});

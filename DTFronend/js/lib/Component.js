export class Component{
  constructor(state,template, container) {
    this.state = state;
    this.template = template;
    this.container = container;
  }
  view(){
    return this.template(this.state);
  }

  addEventListeners(){};


  refresh(){
    this.container.updateDOM();
  }

}

export default Component;

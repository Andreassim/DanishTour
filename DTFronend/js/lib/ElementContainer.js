export class ElementContainer{
  constructor(selector) {
    this.element = document.querySelector(`#${selector}`);
    this.components = [];
  }

  addComponent(component){
    this.components.push(component);
  }

  updateDOM(){
    if(this.components) {
      let mergedViews = '';
      this.components.forEach(component => {
        mergedViews += component.view();

      });
      this.element.innerHTML = mergedViews;
      this.components.forEach(component =>{
        component.addEventListeners();
      })
    }

  }
  clearCompenents(){
    this.components = [];
  }
}


export default ElementContainer;

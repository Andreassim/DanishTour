import Component from "../lib/Component.js";

class TourSelectComponent extends Component {
  constructor(tours, container) {
    super(tours, (state) =>
        `
        <div class="row"
           ${this.renderTourCards(state)}
        </div>

     `
      , container);
  }
  renderTourCards(tours) {
    let template = (state) => `
    <div class="col">
        <div class="card shadow">
             <div class="card-body">
                <div class="row">
                    <div class="col">
                        <div class="card-title">${state.name}</div>
                    </div>
                    <div class="col d-flex justify-content-end">
                        <btn class="btn btn-primary" id="tour${state.id}">Vælg</btn>
                    </div>
                </div>
                <div class="row"></div>
                <div class="card-text">Førende rytter</div>
                ${this.renderLeader(state)}
             </div>
        </div>
    </div>

    `
    return tours.map(tour => template(tour)).join("")
  }

  renderLeader(state){
    if(state.tourResults.length == 0){
      return ``
    }
    let template = (state) => `<ul class="list-group list-group-flush">
                    <li class="list-group-item">${state.tourResults[0].rider.name}</li>
                    <li class="list-group-item">${this.formatTime(state.tourResults[1].totalTime)}</li>
                </ul>

    `

    return template(state);

  }

  addEventListeners() {
    this.state.forEach((tour) => {
      let element = document.getElementById("tour" + tour.id)
      element.addEventListener('click', () => {
        sessionStorage.setItem("selectedTour", JSON.stringify(tour));
        this.refresh()
      })
    })
  }

  formatTime(time){
    let str = time.toString();
    return str.slice(2,str.length) //Hardcoded removing first to Letters in string - Duration comes as PTXXHourYYmmZZss
  }


}

export default TourSelectComponent;

import Component from "../lib/Component.js";

class AddStageResultComponent extends Component {
  constructor(stages, riders, container) {
    let state = {
      stages: stages,
      riders: riders
    }
    super(state, (state) => `
    <form id="addStageResultForm" class="border rounded shadow p-3">
        <div class="mb-3">
            <label for="stage" class="form-label">Etape</label>
            <select id="stage" class="form-select">
              <option></option>
              ${this.renderStageOptions(state.stages)}
            </select>
        </div>
        <div class="mb-3">
            <label for="rider" class="form-label">Rytter</label>
            <select id="rider" class="form-select">
              <option></option>
              ${this.renderRiderOptions(state.riders)}
            </select>
        </div>
        <div class="mb-3">
            <label for="time" class="form-label">Tid (HH:mm:ss)</label>
            <input type="text" class="form-control" id="time">
        </div>
        <div class="mb-3">
            <label for="MP" class="form-label">Bjergpoints</label>
            <input type="number" class="form-control" id="MP">
        </div>
        <div class="mb-3">
            <label for="SP" class="form-label">Springpoints</label>
            <input type="number" class="form-control" id="SP">
        </div>
        <div class="mb-3">
            <label for="tour" class="form-label">Tour</label>
            <input id="tour" type="text" disabled class="form-control" placeholder="${JSON.parse(sessionStorage.getItem("selectedTour")).name}">
        </div>
      <button type="submit" class="btn btn-primary">Tilføj</button>
    </form>
    `, container);
  }

  renderStageOptions(stages) {
    let template = (state) => `<option value="${state.id}">Etape #${state.stageNumber}</option>
    `;
    return stages.map(rider => template(rider)).join("");
  }

  renderRiderOptions(riders) {
    let template = (state) => `<option value="${state.id}">${state.name} (${state.team.acronym})</option>
    `;
    return riders.map(rider => template(rider)).join("");
  }

  addEventListeners() {
    let form = document.getElementById('addStageResultForm');
    form.addEventListener("submit", async (e) => {
      e.preventDefault();
      let stage = document.getElementById('stage').value;

      let endpoint = 'http://localhost:8080/stage/'+ stage +'/addResult'

      let rider = document.getElementById('rider').value;
      let MP = document.getElementById('MP').value;
      let SP = document.getElementById('SP').value;
      let time = document.getElementById('time').value;


      let body = {
        rider:{
          id:rider
        },
        mountainPoints:MP,
        sprintPoints:SP,
        completionTime:convertTime(time)


      }
      console.log(body);

      let options = {
        method:"POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(body)
      }

      let response = await fetch(endpoint,options);
      let json = await response.json();

      await updateTour();
    }


    )

    async function updateTour(){
      let selectedTour = JSON.parse(sessionStorage.getItem("selectedTour"));
      const endpoint = 'http://localhost:8080/tour/'+selectedTour.id+'/update'

      let options ={
        method:"POST"
      }

      let response = await fetch(endpoint,options);
      let json = await response.json();

      sessionStorage.setItem("selectedTour", JSON.stringify(json));

    }

    function convertTime(time){
      console.log(time)
      let str = time.split(":");
      let result = 'PT'+str[0]+'H'+str[1]+'M'+str[2]+'S';

      return result;
    }

  }

}
export default AddStageResultComponent;

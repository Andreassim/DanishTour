import Component from "../lib/Component.js";

class EditRiderComponent extends Component {
  constructor(teams,riders, container) {

    let state ={
      teams: teams,
      rider:riders
    }
    super(state, (state) => `
    <form id="riderForm" class="border rounded shadow p-3">
        <div class="mb-3">
          <label for="rider" class="form-label">Rytter</label>
          <select id="rider" class="form-select" aria-label="Default select example">
            <option></option>
            ${this.renderRiderOptions(state.rider)}
          </select>
         </div>
        <div class="mb-3">
        <label for="newName" class="form-label">Nyt Navn</label>
        <input type="text" class="form-control" id="newName">
         </div>
    <div class="mb-3">
         <label for="age" class="form-label">Ny Alder</label>
         <input type="number" class="form-control" id="age">
         </div>
    <div class="mb-3">
        <label for="team" class="form-label">Hold</label>
        <select id="team" class="form-select" aria-label="Default select example">
          <option></option>
          ${this.renderOptions(state.teams)}
        </select>
       </div>
  <button type="submit" class="btn btn-primary">Tilføj</button>
</form>
    `, container);
  }

  renderOptions(teams) {
    let template = (state) => `<option value="${state.id}">${state.name} (${state.acronym})</option>
    `;
    return teams.map(team => template(team)).join("");
  }

  renderRiderOptions(riders) {
    let template = (state) => `<option value="${state.id}">${state.name} (${state.team.acronym})</option>
    `;
    return riders.map(rider => template(rider)).join("");
  }

  addEventListeners() {
    let form = document.getElementById('riderForm');
    form.addEventListener("submit", async (e) => {
      e.preventDefault();
      let endpoint = 'http://localhost:8080/rider/update'

      let riderid = document.getElementById('rider').value;
      let formName = document.getElementById('newName').value;
      let formAge = document.getElementById('age').value;
      let formTeam = document.getElementById('team').value;


      let body = {
        id:riderid,
        name:formName,
        age:formAge,
        team: {
          id:formTeam
        }
      }
      console.log(body);

      let options = {
        method:"PUT",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(body)
      }

      let response = await fetch(endpoint,options);
      let json = response.json();
      console.log(json);

    })


  }


}

export default EditRiderComponent;

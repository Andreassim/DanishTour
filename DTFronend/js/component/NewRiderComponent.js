import Component from "../lib/Component.js";

class NewRiderComponent extends Component {
  constructor(teams, container) {
    ;
    super(teams, (state) => `
    <form id="riderForm" class="border rounded shadow p-3">
        <div class="mb-3">
        <label for="name" class="form-label">Navn</label>
        <input type="text" class="form-control" id="name">
         </div>
    <div class="mb-3">
         <label for="age" class="form-label">Alder</label>
         <input type="number" class="form-control" id="age">
         </div>
    <div class="mb-3">
        <label for="team" class="form-label">Hold</label>
        <select id="team" class="form-select" aria-label="Default select example">
          <option></option>
          ${this.remderOptions(state)}
        </select>
       </div>
  <button type="submit" class="btn btn-primary">Tilføj</button>
</form>
    `, container);
  }

  remderOptions(teams) {
    let template = (state) => `<option value="${state.id}">${state.name} (${state.acronym})</option>
    `;
    return teams.map(team => template(team)).join("");
  }

  addEventListeners() {
    let form = document.getElementById('riderForm');
    form.addEventListener("submit", async (e) => {
      e.preventDefault();
      let endpoint = 'http://localhost:8080/rider/new'

      let formName = document.getElementById('name').value;
      let formAge = document.getElementById('age').value;
      let formTeam = document.getElementById('team').value;


      let body = {
        name:formName,
        age:formAge,
        team: {
          id:formTeam
        }
      }
      console.log(body);

      let options = {
        method:"POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(body)
      }

      let response = await fetch(endpoint,options);
      let json = response.json();
      console.log(json);

    })


  }


}

export default NewRiderComponent;

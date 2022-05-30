import Component from "../lib/Component.js";

class AddRiderCompoenent extends Component {
  constructor(riders, container) {
    super(riders, (state) => `
<form id="addRiderForm" class="border rounded shadow p-3">
    <div class="mb-3">
        <label for="rider" class="form-label">Hold</label>
        <select id="rider" class="form-select" aria-label="Default select example">
          <option></option>
          ${this.remderOptions(state)}
        </select>
    </div>
    <div class="mb-3">
        <label for="tour" class="form-label">Tour</label>
        <input id="tour" type="text" disabled placeholder="${JSON.parse(sessionStorage.getItem("selectedTour")).name}">
</div>
  <button type="submit" class="btn btn-primary">Tilføj</button>
</form>
    `, container);
  }

  remderOptions(riders) {
    let template = (state) => `<option value="${state.id}">${state.name} (${state.team.acronym})</option>
    `;
    return riders.map(rider => template(rider)).join("");
  }

  addEventListeners() {
    let form = document.getElementById('addRiderForm');
    form.addEventListener("submit", async (e) => {
      e.preventDefault();
      let selectedTour = JSON.parse(sessionStorage.getItem("selectedTour"));
      let endpoint = 'http://localhost:8080/tour/'+ selectedTour.id +'/addRider'

      let formRider = document.getElementById('rider').value;

      let body = {
        id:formRider
      }
      console.log(body);

      let options = {
        method:"POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(body)
      }

      let response = await fetch(endpoint,options);
      let json = await response.json();

      sessionStorage.setItem("selectedTour", JSON.stringify(json));
    })


  }

}
export default AddRiderCompoenent;

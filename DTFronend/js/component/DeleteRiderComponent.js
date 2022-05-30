import Component from "../lib/Component.js";

class DeleteRiderComponent extends Component {
  constructor(riders, container) {
    super(riders, (state) => `
<form id="addRiderForm" class="border rounded shadow p-3">
    <div class="mb-3">
        <label for="rider" class="form-label">Rytter</label>
        <select id="rider" class="form-select" aria-label="Default select example">
          <option></option>
          ${this.renderOptions(state)}
        </select>
    </div>
  <button type="submit" class="btn btn-danger">Slet</button>
</form>
    `, container);
  }

  renderOptions(riders) {
    let template = (state) => `<option value="${state.id}">${state.name} (${state.team.acronym})</option>
    `;
    return riders.map(rider => template(rider)).join("");
  }

  addEventListeners() {
    let form = document.getElementById('addRiderForm');
    form.addEventListener("submit", async (e) => {
      e.preventDefault();
      let rider = document.getElementById('rider').value;
      let endpoint = 'http://localhost:8080/rider/delete/' + rider

      let options = {
        method:"DELETE",

      }

      let response = await fetch(endpoint,options);
      let json = await response.json();

      await updateTour();
      this.refresh();
    })

    async function updateTour(){
      let selectedTour = JSON.parse(sessionStorage.getItem("selectedTour"));
      const endpoint = 'http://localhost:8080/tour/'+selectedTour.id+'/update'

      let options ={
        method:"POST",
        headers: {"Content-Type": "application/json"},
      }

      let response = await fetch(endpoint,options);
      let json = await response.json();

      sessionStorage.setItem("selectedTour", JSON.stringify(json));

    }

  }

}
export default DeleteRiderComponent;

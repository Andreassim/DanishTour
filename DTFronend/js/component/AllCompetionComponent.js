import Component from "../lib/Component.js";

class AllCompetionComponent extends Component {
  constructor(results, container) {
    let state = {
      yellow: results.totalTime,
      dotted: results.mountainPoints,
      green: results.sprintPoints,
      white: results.age
    }
    super(state, (state) => `
            <H2>Den Gule Trøje</H2>
            <table class="table table-striped border shadow">
                <thead style="background-color: Yellow">
                    <th scope="col">#</th>
                    <th scope="col">Navn</th>
                    <th scope="col">Alder</th>
                    <th scope="col">Hold</th>
                    <th scope="col">Tid</th>
                </thead>
                <tbody>
                ${this.renderTableTime(state.yellow)}
                </tbody>
            </table>
            <H2>Ungdomstrøje</H2>
             <table class="table table-striped border shadow">
                <thead style="background-color: White">
                    <th scope="col">#</th>
                    <th scope="col">Navn</th>
                    <th scope="col">Alder</th>
                    <th scope="col">Hold</th>
                    <th scope="col">Tid</th>
                </thead>
                <tbody>
                ${this.renderTableTime(state.white)}
                </tbody>
                </table>
                <H2>Den Grønne trøje</H2>
            <table class="table table-striped border shadow">
                <thead style="background-color: greenyellow">
                    <th scope="col">#</th>
                    <th scope="col">Navn</th>
                    <th scope="col">Alder</th>
                    <th scope="col">Hold</th>
                    <th scope="col">Point</th>
                </thead>
                <tbody>
                ${this.renderSPTable(state.green)}
                </tbody>
                </table>

             <H2>Den Plettede Bjergtrøje</H2>
             <table class="table table-striped border shadow" style="border: dotted">
                <thead style="background-color: Red">
                    <th scope="col">#</th>
                    <th scope="col">Navn</th>
                    <th scope="col">Alder</th>
                    <th scope="col">Hold</th>
                    <th scope="col">Point</th>
                </thead>
                <tbody>
                ${this.renderMPTable(state.dotted)}
                </tbody>
                </table>

    `, container);
  }

  renderTableTime(result) {
    let response = ``;
    let limit = result.length < 10 ? result.length : 10;
    for (let i = 0; i < limit; i++) {
      response += `
        <tr>
          <th scope="col">${i+1}</td>
          <td>${result[i].rider.name}</td>
          <td>${result[i].rider.age}</td>
          <td>${result[i].rider.team.acronym}</td>
          <td>${this.formatTime(result[i].totalTime)}</td>
        </tr>
      `
    }
    return response;

  }

  renderSPTable(result) {
    let response = ``;
    let limit = result.length < 10 ? result.length : 10;
    for (let i = 0; i < limit; i++) {
      response += `
        <tr>
          <th scope="col">${i+1}</td>
          <td>${result[i].rider.name}</td>
          <td>${result[i].rider.age}</td>
          <td>${result[i].rider.team.acronym}</td>
          <td>${result[i].totalSprintPoints}</td>
        </tr>
      `
    }
    return response;

  }

  renderMPTable(result) {
    let response = ``;
    let limit = result.length < 10 ? result.length : 10;
    for (let i = 0; i < limit; i++) {
      response += `
        <tr>
          <th scope="col">${i+1}</td>
          <td>${result[i].rider.name}</td>
          <td>${result[i].rider.age}</td>
          <td>${result[i].rider.team.acronym}</td>
          <td>${result[i].totalMountainPoints}</td>
        </tr>
      `
    }
    return response;

  }

  formatTime(time) {
    let str = time.toString();
    return str.slice(2, str.length) //Hardcoded removing first to Letters in string - Duration comes as PTXXHourYYmmZZss
  }
}

export default AllCompetionComponent;

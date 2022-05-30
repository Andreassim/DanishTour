import Component from "../lib/Component.js";


class PelotonComponent extends Component {
  constructor(results, container) {
    super(results, (state) => `
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
                ${this.renderTableTime(state)}
                </tbody>
    `, container);
  }

  renderTableTime(result) {
    let response = ``;
    for (let i = 0; i < result.length; i++) {
      response += `
        <tr>
          <th scope="col">${i + 1}</td>
          <td>${result[i].rider.name}</td>
          <td>${result[i].rider.age}</td>
          <td>${result[i].rider.team.acronym}</td>
          <td>${this.formatTime(result[i].totalTime)}</td>
        </tr>
      `
    }
    return response;

  }
  formatTime(time){
    let str = time.toString();
    return str.slice(2,str.length) //Hardcoded removing first to Letters in string - Duration comes as PTXXHourYYmmZZss
  }
}
export default PelotonComponent;

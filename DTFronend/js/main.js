import TourSelectComponent from "./component/TourSelectComponent.js";
import ElementContainer from "./lib/ElementContainer.js";
import AllCompetionComponent from "./component/AllCompetionComponent.js";
import PelotonComponent from "./component/PelotonComponent.js";
import NewRiderComponent from "./component/NewRiderComponent.js";
import addRiderComponent from "./component/AddRiderComponent.js";
import AddStageResultComponent from "./component/AddStageResultComponent.js";
import deleteRiderComponent from "./component/DeleteRiderComponent.js";
import EditRiderComponent from "./component/EditRiderComponent.js";
import TeamCompetionComponent from "./component/TeamCompetionComponent.js";


let contentContainer = new ElementContainer("content")
let content;

document.getElementById('tour-link').addEventListener('click', async () => {
  changeActive(document.getElementById('tour-link'))
  let tours = await fetchTours();
  content = new TourSelectComponent(tours,contentContainer);
  contentContainer.clearCompenents();
  contentContainer.addComponent(content);
  contentContainer.updateDOM();
})

document.getElementById('leaders-link').addEventListener('click', async () => {
  changeActive(document.getElementById('leaders-link'))
  let results = await fetchTotals();
  content = new AllCompetionComponent(results, contentContainer)
  contentContainer.clearCompenents();
  contentContainer.addComponent(content);
  contentContainer.updateDOM();
})

document.getElementById('team-link').addEventListener('click', async () => {
  changeActive(document.getElementById('team-link'))
  let results = await fetchFastestTeams()
  content = new TeamCompetionComponent(results, contentContainer);
  contentContainer.clearCompenents();
  contentContainer.addComponent(content);
  contentContainer.updateDOM();
})

document.getElementById('all-link').addEventListener('click', async () => {
  changeActive(document.getElementById('all-link'))
  let result = await fetchFastest();
  content = new PelotonComponent(result,contentContainer);
  contentContainer.clearCompenents();
  contentContainer.addComponent(content);
  contentContainer.updateDOM();
})

document.getElementById('addRider-link').addEventListener('click', async () => {
  changeActive(document.getElementById('addRider-link'))
  let riders = await fetchRiders();
  content = new addRiderComponent(riders, contentContainer);
  contentContainer.clearCompenents();
  contentContainer.addComponent(content);
  contentContainer.updateDOM();
})


document.getElementById('newRider-link').addEventListener('click', () => {
  changeActive(document.getElementById('newRider-link'))
  let tour = JSON.parse(sessionStorage.getItem("selectedTour"))
  content = new NewRiderComponent(tour.teams, contentContainer)
  contentContainer.clearCompenents();
  contentContainer.addComponent(content);
  contentContainer.updateDOM();
})

document.getElementById('addStageResult-link').addEventListener('click', () => {
  changeActive(document.getElementById('addStageResult-link'))
  let tour = JSON.parse(sessionStorage.getItem("selectedTour"))
  content = new AddStageResultComponent(tour.stages,tour.riders, contentContainer)
  contentContainer.clearCompenents();
  contentContainer.addComponent(content);
  contentContainer.updateDOM();
})

document.getElementById('deleteRider-link').addEventListener('click', () => {
  changeActive(document.getElementById('deleteRider-link'))
  let tour = JSON.parse(sessionStorage.getItem("selectedTour"))
  content = new deleteRiderComponent(tour.riders, contentContainer)
  contentContainer.clearCompenents();
  contentContainer.addComponent(content);
  contentContainer.updateDOM();
})

document.getElementById('editRider-link').addEventListener('click', () => {
  changeActive(document.getElementById('editRider-link'))
  let tour = JSON.parse(sessionStorage.getItem("selectedTour"))
  content = new EditRiderComponent(tour.teams,tour.riders, contentContainer)
  contentContainer.clearCompenents();
  contentContainer.addComponent(content);
  contentContainer.updateDOM();
})

function changeActive(element) {
  let list = element.closest("ul")
  let active = list.querySelector('.active')
  if(active != undefined){
  active.classList.remove('active')
  active.classList.add('link-dark')
  }
  element.classList.add('active')
  element.classList.remove('link-dark')
}


async function fetchTours() {
  const endpoint = 'http://localhost:8080/tour/all'

  let response = await fetch(endpoint);
  let json = await response.json();

  return json;

}

async function fetchTotals() {
  let selected = JSON.parse(sessionStorage.getItem("selectedTour"));

  const endpoint = 'http://localhost:8080/tour/'+selected.id+'/results'

  let response = await fetch(endpoint);
  let json = await response.json();

  return json;
}

async function fetchFastest() {
  let selected = JSON.parse(sessionStorage.getItem("selectedTour"));

  const endpoint = 'http://localhost:8080/tour/'+selected.id+'/fastest'

  let response = await fetch(endpoint);
  let json = await response.json();

  return json;
}

async function fetchRiders(){
  let selectedTour = JSON.parse(sessionStorage.getItem("selectedTour"));
  const endpoint = 'http://localhost:8080/tour/'+selectedTour.id+'/availableRiders'

  let response = await fetch(endpoint);
  let json = await response.json();

  return json;

}

async function fetchFastestTeams() {

  const endpoint = 'http://localhost:8080/team/fastest'

  let response = await fetch(endpoint);
  let json = await response.json();

  return json;
}

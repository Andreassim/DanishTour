import TourSelectComponent from "./component/TourSelectComponent.js";
import ElementContainer from "./lib/ElementContainer.js";
import AllCompetionComponent from "./component/AllCompetionComponent.js";
import PelotonComponent from "./component/PelotonComponent.js";


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

document.getElementById('team-link').addEventListener('click', () => {
  changeActive(document.getElementById('team-link'))
  content = new TourSelectComponent();
  contentContainer.clearCompenents();
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

document.getElementById('country-link').addEventListener('click', () => {
  changeActive(document.getElementById('country-link'))
  content = new TourSelectComponent();
  contentContainer.clearCompenents();
  contentContainer.updateDOM();
})

document.getElementById('rider-link').addEventListener('click', () => {
  changeActive(document.getElementById('rider-link'))
  content = new TourSelectComponent();
  contentContainer.clearCompenents();
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



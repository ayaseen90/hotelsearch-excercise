import { Component, OnInit, Input, ViewChild, ElementRef } from '@angular/core';
import { Hotel } from '../model';

@Component({
  selector: 'app-hotelrating',
  templateUrl: './hotelrating.component.html',
  styleUrls: ['./hotelrating.component.css']
})
export class HotelRatingComponent implements OnInit {

  constructor() { }

  @Input()
  value : number;


  numOfStars: number = 5;

  @ViewChild('starsDrawer') starsDrawer: ElementRef;
  
      loadData() {
          this.starsDrawer.nativeElement.innerHTML = this.drawStars();
      }

  ngOnInit() {
    this.loadData();
  }

  public drawStars() : string {
    let result: string = '';
    let counter: number = 0;

    while(counter < this.numOfStars && counter < Math.floor(this.value)) {
      result += '<span class="fa fa-star checked"></span>';
      counter++;
    }

    if(counter < this.numOfStars  && counter < this.value) {
      result += '<span class="fa fa-star-half-full checked"></span>'; 
      counter++;
    }

    while(counter < this.numOfStars) {
      result += '<span class="fa fa-star-o"></span>';
      counter++;
    }


    return result;
  }

}

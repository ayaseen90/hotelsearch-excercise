import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelRatingComponent } from './hotelrating.component';

describe('HotelratingComponent', () => {
  let component: HotelRatingComponent;
  let fixture: ComponentFixture<HotelRatingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotelRatingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotelRatingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

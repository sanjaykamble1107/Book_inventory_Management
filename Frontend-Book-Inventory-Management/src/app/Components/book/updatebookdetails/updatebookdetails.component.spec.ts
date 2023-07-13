import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatebookdetailsComponent } from './updatebookdetails.component';

describe('UpdatebookdetailsComponent', () => {
  let component: UpdatebookdetailsComponent;
  let fixture: ComponentFixture<UpdatebookdetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdatebookdetailsComponent]
    });
    fixture = TestBed.createComponent(UpdatebookdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

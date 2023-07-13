import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewBookReviewComponent } from './add-new-book-review.component';

describe('AddNewBookReviewComponent', () => {
  let component: AddNewBookReviewComponent;
  let fixture: ComponentFixture<AddNewBookReviewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddNewBookReviewComponent]
    });
    fixture = TestBed.createComponent(AddNewBookReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

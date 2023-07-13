import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateBookReviewComponent } from './update-book-review.component';

describe('UpdateBookReviewComponent', () => {
  let component: UpdateBookReviewComponent;
  let fixture: ComponentFixture<UpdateBookReviewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateBookReviewComponent]
    });
    fixture = TestBed.createComponent(UpdateBookReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

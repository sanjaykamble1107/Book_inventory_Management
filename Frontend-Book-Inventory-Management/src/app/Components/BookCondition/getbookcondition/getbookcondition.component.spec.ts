import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetbookconditionComponent } from './getbookcondition.component';

describe('GetbookconditionComponent', () => {
  let component: GetbookconditionComponent;
  let fixture: ComponentFixture<GetbookconditionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetbookconditionComponent]
    });
    fixture = TestBed.createComponent(GetbookconditionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

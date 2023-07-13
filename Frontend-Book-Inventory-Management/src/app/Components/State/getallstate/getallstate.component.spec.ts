import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetallstateComponent } from './getallstate.component';

describe('GetallstateComponent', () => {
  let component: GetallstateComponent;
  let fixture: ComponentFixture<GetallstateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetallstateComponent]
    });
    fixture = TestBed.createComponent(GetallstateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import {Injectable} from 'angular2/core';
import {Hero} from './hero';
import {HEROES} from './mock-heroes';
@Injectable() //别忘了括号
export class HeroService {
  getHeroes() {
    return Promise.resolve(HEROES);
  }
  getHero(id: number) {
    return Promise.resolve(HEROES).then(
      heroes => heroes.filter(hero => hero.id === id)[0]
    );
  }
}
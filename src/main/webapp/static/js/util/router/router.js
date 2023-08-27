import { paths } from './paths.js';
import { errorView } from '../../view.js';

export default function Router() {
  this.getMatcher = (hash) => {
    const regExp = /\d+/g;
    const args = [];
    const url = hash.split('/');

    url.forEach((param) => {
      if (param.match(regExp)) {
        args.push(param);
      }
    });

    return args;
  };

  this.dispatch = (hash) => {
    const args = this.getMatcher(hash);
    const deptId = args[0];
    const emplId = args[1];

    const hashWithPatter = hash.replace(/\d+/g, ':id');
    let route;

    paths.forEach((path) => {
      if (path.path === (hashWithPatter)) {
        route = path;
        route.view(deptId, emplId);
        route.command(deptId, emplId);
      }
    });
    if (!route) {
      errorView('404 Page Not Found');
    }
  };
}

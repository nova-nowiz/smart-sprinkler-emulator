{
  description = "Developement environment for Nix users";

  inputs.devshell.url = "github:numtide/devshell";
  inputs.flake-utils.url = "github:numtide/flake-utils";

  outputs = { self, flake-utils, devshell, nixpkgs }:
    flake-utils.lib.eachDefaultSystem (system: {
      devShell =
        let pkgs = import nixpkgs {
          inherit system;

          overlays = [ devshell.overlay ];
        };
        in
        pkgs.devshell.mkShell {
          imports = [ "${pkgs.devshell.extraModulesDir}/language/c.nix" ];
          commands = [
            {
              package = pkgs.devshell.cli;
              help = "Per project developer environments";
            }
            {
              name = "run";
              command = "kotlinc main.kt -include-runtime -d main.jar && kotlin main.jar";
              help = "runs this project";
            }
            {
              name = "build";
              command = "kotlinc main.kt -include-runtime -d main.jar";
              help = "builds this project";
            }
          ];
          devshell.packages = with pkgs; [
            kotlin
            gitflow
          ];
          language.c.libraries = with pkgs; [
          ];
        };
    });
}

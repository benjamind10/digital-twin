{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
  buildInputs = [
    pkgs.openjdk17
    pkgs.maven
  ];

  shellHook = ''
    echo "Java 17 + Maven environment for Digital Twin ready."
  '';
}

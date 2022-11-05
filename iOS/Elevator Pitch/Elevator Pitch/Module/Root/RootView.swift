//
//  RootView.swift
//  Elevator Pitch
//
//  Created by Yusuf on 5.11.2022.
//

import SwiftUI

struct RootView: View {
    @ObservedObject var viewModel: RootViewModel
    
    init(viewModel: RootViewModel = RootViewModel()) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        if viewModel.isLogin {
            HomeView()
        } else {
            RegisterView()
        }
    }
}

struct RootView_Previews: PreviewProvider {
    static var previews: some View {
        RootView()
    }
}
